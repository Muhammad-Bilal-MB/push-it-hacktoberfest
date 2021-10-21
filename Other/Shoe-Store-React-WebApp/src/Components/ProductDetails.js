import React from 'react';
import { Outlet, useParams } from 'react-router-dom';
import ShoesData from '../ProductsData/Shoes.json';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import { Grid, Typography } from '@material-ui/core';


const useStyles = makeStyles((theme) => ({
    root: {
        // margin: '100px',
        
          flexGrow: 1,
       
      p:{
          textAlign: "center"
      },
      paper:{
        backgroundColor: "#be1d1d",
        padding: theme.spacing(2),
      },
      desc:{
          margin:"10"
      }
    },
  }));

const ProductDetails = () => {

    const classes = useStyles();

    const {productID} = useParams();

    const product = ShoesData[productID];
    console.log(product);

    const {title, image, description, availableSizes, price } = product

    console.log(title);

    console.log(productID);
    return ( 
      <>
      {/* <Navbar /> */}
        <div className={classes.root} style = {{textAlign :"center"}}>
        <h1 style ={{textAlign:"center"}}>Item Details</h1>

          <Grid container 
          spacing={3}
          direction ="column"
          alignItems ="center"
          justify= "center"  >
           <Grid item xs={12} sm ={6} xs-center = "true">
            <Paper style = {{backgroundColor: "#be1d1d", color:"white"}} className= {classes.paper} elevation={3}>
            <br/>
            <h1 style ={{fontSize: "3rem"}}>{title}</h1>
            <img src ={image} alt = {title} width = "100%" height ="2%" />
            
            
            <Typography  style ={{padding:"3px",textAlign:"center", margin:"20px", fontSize: "20px"}}>{description}</Typography>
            <p style = {{fontSize: "1.5rem"}}><strong>Avalaible Sizes:</strong> {availableSizes.map(size => size + "   "  )}</p>
            <h2>Price: $ {price}</h2>
            <br /> <br />
            </Paper>
            </Grid>
            </Grid>
            <Outlet />
        </div>
        </>
     );
}
 
export default ProductDetails;