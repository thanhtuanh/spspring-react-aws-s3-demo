import React, { useState, useRef } from 'react';
import axios from '../services/api';

export default function ProductForm({ onUploadSuccess }) {
  const [title, setTitle] = useState('');
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState('');
  const [previewUrl, setPreviewUrl] = useState('');
  const [metadata, setMetadata] = useState(null);
  const [error, setError] = useState('');
  const [uploadProgress, setUploadProgress] = useState(0);

  const dropRef = useRef(null);
  const MAX_FILE_SIZE = 10 * 1024 * 1024;

  const handleFile = (selectedFile) => {
    if (!selectedFile) return;

    if (selectedFile.size > MAX_FILE_SIZE) {
      setError(`❌ Die Datei ist zu groß! Max. ${(MAX_FILE_SIZE / 1024 / 1024).toFixed(0)} MB erlaubt.`);
      return;
    }

    const objectUrl = URL.createObjectURL(selectedFile);
    setFile(selectedFile);
    setPreviewUrl(objectUrl);
    setError('');

    const img = new Image();
    img.src = objectUrl;
    img.onload = () => {
      setMetadata({
        name: selectedFile.name,
        size: (selectedFile.size / 1024).toFixed(2) + ' KB',
        type: selectedFile.type,
        width: img.width,
        height: img.height,
      });
    };
  };

  const handleFileChange = (e) => handleFile(e.target.files[0]);

  const handleDrop = (e) => {
    e.preventDefault();
    const droppedFile = e.dataTransfer.files[0];
    handleFile(droppedFile);
  };

  const handleDragOver = (e) => e.preventDefault();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!title || !file) {
      setMessage('Bitte Titel und Datei auswählen.');
      return;
    }

    const formData = new FormData();
    formData.append('title', title);
    formData.append('file', file);

    try {
      const res = await axios.post('/products', formData); // KEIN Content-Type setzen!
      setMessage(`✅ Erfolgreich hochgeladen: ${res.data.title}`);
      setTitle('');
      setFile(null);
      setPreviewUrl('');
      setMetadata(null);
      setUploadProgress(0);
      if (onUploadSuccess) onUploadSuccess(); // <-- WICHTIG: Nach Erfolgreichem Upload
    } catch (err) {
      console.error('❌ Upload fehlgeschlagen:', err);
      setMessage('❌ Upload fehlgeschlagen.');
    }
  };

  return (
    <form onSubmit={handleSubmit} className="mb-4">
      <div className="card p-4 shadow-sm">
        <h5 className="mb-3">📤 Neues Produkt hochladen</h5>

        <div className="mb-3">
          <label className="form-label">Titel</label>
          <input
            type="text"
            className="form-control"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>

        <div
          className="mb-3 p-3 border border-dashed rounded bg-light text-center"
          onDrop={handleDrop}
          onDragOver={handleDragOver}
          ref={dropRef}
          style={{ cursor: 'pointer' }}
        >
          <label className="form-label">Datei hierher ziehen oder auswählen</label>
          <input type="file" className="form-control" onChange={handleFileChange} />
        </div>

        {uploadProgress > 0 && (
          <div className="mb-3">
            <label>Fortschritt:</label>
            <div className="progress">
              <div
                className="progress-bar progress-bar-striped bg-success"
                style={{ width: `${uploadProgress}%` }}
              >
                {uploadProgress}%
              </div>
            </div>
          </div>
        )}

        <button type="submit" className="btn btn-primary w-100" disabled={!!error}>
          Hochladen
        </button>

        {error && <div className="mt-3 alert alert-danger">{error}</div>}
        {message && <div className="mt-3 alert alert-info">{message}</div>}

        {previewUrl && (
          <div className="mt-3 text-center">
            <p>Bildvorschau:</p>
            <img
              src={previewUrl}
              alt="Vorschau"
              className="img-thumbnail"
              style={{
                maxWidth: '140px',
                maxHeight: '140px',
                objectFit: 'cover',
                borderRadius: '0.5rem',
                border: '1px solid #dee2e6',
                boxShadow: '0 2px 8px rgba(33,37,41,0.07)'
              }}
            />
          </div>
        )}

        {metadata && (
          <div className="mt-3">
            <h6>📄 Bild-Metadaten:</h6>
            <ul className="list-group">
              <li className="list-group-item">📁 <strong>Dateiname:</strong> {metadata.name}</li>
              <li className="list-group-item">🧾 <strong>Typ:</strong> {metadata.type}</li>
              <li className="list-group-item">💾 <strong>Größe:</strong> {metadata.size}</li>
              <li className="list-group-item">📐 <strong>Abmessungen:</strong> {metadata.width}×{metadata.height}px</li>
            </ul>
          </div>
        )}
      </div>
    </form>
  );
}