import React, {useState} from 'react';
import searchService from './searchService';

function ProductGroupEditor(props) {
  // useState is like instance variables for React functional components
  const [products, setProducts] = useState(props.productGroup?.products || []);
  const onAdd = (t) => setProducts([...products, t]);

  return (
    <div className="admin">
      <h1>Edit ProductGroup</h1>
      <SearchBox onAdd={onAdd} />
      <form data-testid="product-group" method="POST" action={`/admin/group/${props.productGroup?.id}`}>
        <button type="submit" className="save-button">Save</button>
        {products.map((product, i) => {
          return (
            <div key={i} className="album-container">
              <Album product={product}/>
              <input name="items" type="hidden" value={product.id} />
            </div>
          );
        })}

      </form>
    </div>
  );
}

function Album(props) {
  const artist = props.product.artists[0].name;

  return (
    <div data-testid={'album'} className="album">
      <img
        alt={`${artist} - ${props.product.albumTitle}`}
        src={props.product.imageUrl}/>
      <div className="details">
        <p>{artist}</p>
        <p>{props.product.albumTitle}</p>
      </div>
    </div>
  );
}

function SearchBox(props) {
  const [results, setResults] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  return (
    <div className="search-container">
      <input data-testid="search-input"
        id="product-search" value={searchTerm}
        type="text"
        onChange={(event) => setSearchTerm(event.target.value)}/>
      <button type="button" data-testid="search-button" id="search-button"
        onClick={async () => setResults(await searchService(searchTerm))}
      >Search</button>
      {
        results?.length > 0 && <div data-testid="search-results" className="search-results">
          {results.map((result, i) => {
            return (
              <div key={i} className="result">
                <Album product={result}/>
                <div className="button-container">
                  <button id={`album-${result.id}`} onClick={() => props.onAdd(result)} className="add-button">Add</button>
                </div>
              </div>
            );
          })}
        </div>
      }
    </div>
  );
}

export default ProductGroupEditor;
