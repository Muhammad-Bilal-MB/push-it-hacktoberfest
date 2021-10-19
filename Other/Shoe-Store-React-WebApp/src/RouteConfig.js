import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from './Components/Home';
import Products from './Components/Products';
import About from './Components/About';
import ProductIndex from './Components/ProductIndex';
import ProductDetails from './Components/ProductDetails';
import PageNotFound from './Components/PageNotFound';
import Cart from './Components/Cart';




function RouteConfig() {
    return (
      <div>
      <Routes>
          <Route path = "/" element ={<Home />} />
          <Route path = "/about" element ={<About />} />
          <Route path = "/products" element ={<Products />}>
              <Route path ="/" element = {<ProductIndex />}/>
               <Route path =":productID" element = {<ProductDetails />} />
          </Route>
        <Route path = "*" element = {<PageNotFound />} />
        <Route path ="/cart" element ={<Cart /> }/>
      </Routes>

     </div>
    );
  }
  
  export default RouteConfig;
  