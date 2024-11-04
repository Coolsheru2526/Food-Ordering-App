import React from 'react'
import { Route, Routes } from 'react-router-dom'
import { Home } from '@mui/icons-material'
import Restaurant from '../component/Restaurant/RestaurantDetails'
import Cart from '../component/Cart/Cart'
import Profile from '../component/Profile/Profile'



const CustomerRoutes = () => {
  return (
    <div className='relative'>
        <nav className="sticky top-0 z-50">
            <Navbar/>
        </nav>
        <Routes>
            <Route exact path='/' element={<Home/>}/>
            <Route exact path='/account/:register' element={<Home/>}/>
            <Route exact path='/restaurant/:city/:title/:id' element={<Restaurant/>}/>
            <Route path='/cart' element={<Cart/>}/>
            <Route path='/my-profile/*' element={<Profile/>}/>
        </Routes>
    </div>
  )
}

export default CustomerRoutes