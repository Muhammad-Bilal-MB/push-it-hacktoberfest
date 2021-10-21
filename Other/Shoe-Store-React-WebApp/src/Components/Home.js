import React from 'react'
import backgroundImage from '../Images/shoe-store.jpg';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import { Link } from "react-router-dom";
import {ShoppingCart} from '@material-ui/icons'
import Navbar from './Navbar';

const useStyles = makeStyles((theme) => ({
    button: {
    //   margin: theme.spacing(1),
      color: "white",
      backgroundColor: "#C21C1C",
      fontSize: "1.5rem",
      '&.hover':{
          backgroundColor:"white"
      }
    },
  }));

const bgStyle  = {
    backgroundImage: `url(${backgroundImage})`,
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: '100vw',
    height: '100vh'
}


const Home = () => {

    const classes = useStyles();
    return (
        <div style ={bgStyle}>
        <Navbar />
            <div  className ="text-center" >

            <Link to="/products" style={{ textDecoration: 'none' }}>
            <Button style ={{marginTop: "150px", marginLeft:"50px", marginRight:"50px"}}
            className ={classes.button}
            variant="contained" 
            color ="secondary"
            size="large"
            endIcon={<ShoppingCart fontSize = 'large'  />}
            >Lets Go Shopping!</Button>
            </Link>
            </div>
        </div>
    )
}
export default Home;