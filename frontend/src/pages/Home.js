import React, { useState } from 'react';
import ProductForm from '../components/ProductForm';
import ProductList from '../components/ProductList';

export default function Home() {
  const [reload, setReload] = useState(false);

  const handleUploadSuccess = () => {
    setReload(prev => !prev); // Toggle reload
  };

  return (
    <div className="container mt-4">
      <h1 className="mb-4">Produktverwaltung</h1>
      <div className="row">
        <div className="col-md-6">
          <ProductForm onUploadSuccess={handleUploadSuccess} />
        </div>
        <div className="col-md-6">
          <ProductList reloadTrigger={reload} />
        </div>
      </div>
    </div>
  );
}
