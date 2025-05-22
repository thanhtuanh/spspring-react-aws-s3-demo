import React, { useEffect, useState } from 'react';
import axios from '../services/api';

export default function ProductList({ reloadTrigger }) {
  const [products, setProducts] = useState([]);
  const [presignedUrls, setPresignedUrls] = useState({});

  const fetchProducts = async () => {
    try {
      const res = await axios.get("/products");
      const fetchedProducts = res.data;

      const urlMap = {};

      await Promise.all(
        fetchedProducts.map(async (product) => {
          if (product.filename) {
            try {
              console.log("🔍 Lade Presigned URL für:", product.filename);
              const urlRes = await axios.get(`/products/presigned-url/${product.filename}`);
              urlMap[product.id] = urlRes.data;
            } catch (err) {
              console.error(`❌ Fehler bei Presigned URL für ${product.filename}`, err);
            }
          }
        })
      );

      setProducts(fetchedProducts);
      setPresignedUrls(urlMap);
    } catch (err) {
      console.error("❌ Fehler beim Laden der Produkte:", err);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, [reloadTrigger]);

  const handleDelete = async (id) => {
    if (!id || !window.confirm("Produkt wirklich löschen?")) return;

    try {
      await axios.delete(`/products/${id}`);
      fetchProducts();
    } catch (err) {
      console.error("❌ Fehler beim Löschen:", err);
    }
  };

  return (
    <div className="row">
      {products.map((product) => (
        <div className="col-md-6 mb-4" key={product.id}>
          <div className="card h-100">
            {presignedUrls[product.id] ? (
              <img
                src={presignedUrls[product.id]}
                className="card-img-top"
                alt={product.title}
                style={{ objectFit: 'cover', height: '200px' }}
              />
            ) : (
              <div className="card-img-top text-center p-5 text-muted">
                Kein Bild
              </div>
            )}
            <div className="card-body d-flex flex-column">
              <h5 className="card-title">{product.title}</h5>
              {product.filename && (
                <>
                  <p className="mb-1"><strong>Dateiname:</strong> {product.filename}</p>
                  <p className="mb-1"><strong>Typ:</strong> {product.filetype}</p>
                  <p className="mb-1"><strong>Größe:</strong> {(product.filesize / 1024).toFixed(2)} KB</p>
                  <p className="mb-1"><strong>Abmessungen:</strong> {product.width}×{product.height}px</p>
                </>
              )}
              <button
                className="btn btn-danger mt-auto"
                onClick={() => handleDelete(product.id)}
              >
                Löschen
              </button>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}
