import React from 'react'
import { Outlet } from 'react-router-dom'

// import { Box } from "@material-ui/core";
import {  Container, Box } from "@material-ui/core"
import Navbar from './Navbar'






const Products = () => {

    
    // console.log(Object.keys(ShoesData))
    // console.log(Object.entries(ShoesData))
    return (
        <div>
            <Navbar />
            <Box>
  
        <Container>
          <br />
          <br />
          <Outlet />
          <br />
        </Container>
        </Box>

        </div>


    )
}

export default Products;