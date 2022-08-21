import ReactDOM from 'react-dom';
import React from 'react';
import ProductGroupEditor from './ProductGroupEditor.jsx';

window.onload = () => {
  const productGroup = JSON.parse(document.getElementById('products')?.value);
  ReactDOM.render(
      <ProductGroupEditor productGroup={productGroup}/>,
      document.getElementById('app'),
  );
};
