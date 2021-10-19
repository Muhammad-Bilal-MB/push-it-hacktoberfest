import React from 'react';
import { ShoppingCart as ShoppingCartIcon, ShoppingBasket } from '@material-ui/icons/'
import { AppBar, Badge, Toolbar, Button, Typography, withStyles, makeStyles } from '@material-ui/core'
import IconButton from '@material-ui/core/IconButton';
// import MenuIcon from '@material-ui/icons/Menu';

import { Link } from "react-router-dom";

// function LinkTab(props) {
//   return (
//     <Tab
//       component="a"
//       onClick={(event) => {
//         event.preventDefault();
//       }}
//       {...props}
//     />
//   );
// }

const StyledBadge = withStyles((theme) => ({
  badge: {
    right: -3,
    top: 13,
    border: `2px solid ${theme.palette.background.paper}`,
    padding: '0 4px',
  },
}))(Badge);

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    hover: "yellow"
  },
  appbar: {
    // background: "-webkit-linear-gradient(to right, #333333, #dd1818)", /* Chrome 10-25, Safari 5.1-6 */
    background: "linear-gradient(to right, #333333, #dd1818)",
  },
  tabs: {
    marginLeft: "100%"
  },
  navbar: {
    // display: "flex",
    width: '100%',
    flexDirection: "row",
    // overflow: 'auto'
    listStyleType: 'none',
    wordSpacing: '10'
  },
  navItems: {
    listStyleType: 'none',
    // float: 'left',
    padding: '5 10',
    color: 'white',
    textDecoration: 'none',
    fontSize: '17px',
    width: '100%',
    // textAlign: 'center',
    // display: 'block'

  }
}));


const Navbar = () => {

  const classes = useStyles();
  return (


    
      <div className={classes.root}>
        <AppBar position="static" className={classes.appbar}>
          <Toolbar>
            <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
              {/* <MenuIcon /> */}

            </IconButton>
            <ShoppingCartIcon style={{ paddingRight: 12, fontSize: 40 }} />
            <Typography variant="h4" className={classes.title}>

          {/* <Link
            className="styled-link"
            style={{ textDecoration: "none", color: "white" }}
            to="/"
          >
            Shopping Cart
            </Link> */}
             
             <div className ={classes.navbar}>
               <Link className ={classes.navItems} to  ="/" >Home</Link> {' '}
               <Link className ={classes.navItems} to  ="/about" >About</Link> {' '}
               <Link className ={classes.navItems} to  ="/products" >Products</Link>{ ' '}
               </div>
       
          </Typography>
          {/* <Button color="inherit">Login</Button> */}
          <Link to="/cart/">
            <Button variant="outlined" size="small">
              <StyledBadge badgeContent={4} color="secondary">
                <ShoppingBasket style={{ color: "white" }} />

              </StyledBadge>
            </Button>
          </Link>
        </Toolbar>
       
      </AppBar>

      
    </div>
   
  )
}

export default Navbar;