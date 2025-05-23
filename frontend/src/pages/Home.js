import React, { useState } from 'react';
import ProductForm from '../components/ProductForm';
import ProductList from '../components/ProductList';

export default function Home() {
  const [reload, setReload] = useState(false);

  const handleUploadSuccess = () => {
    setReload(prev => !prev);
  };

  return (
    <div className="container py-5">
      <div className="bg-white p-4 rounded shadow-sm mb-4">
        <h1 className="text-primary mb-2">🛒 Produktverwaltung</h1>
        <p className="text-muted mb-0">Verwalten Sie Ihre Produktbilder zentral und sicher.</p>
      </div>
      <div className="row gx-4 gy-4">
        <div className="col-lg-5">
          <ProductForm onUploadSuccess={handleUploadSuccess} />
        </div>
        <div className="col-lg-7">
          <ProductList reloadTrigger={reload} />
        </div>
      </div>
    </div>
  );
}
