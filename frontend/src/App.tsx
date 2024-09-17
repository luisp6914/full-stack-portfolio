import { Route, Routes } from 'react-router-dom'
import Navbar from './common/Navbar'
import Footer from './common/Footer'
import HomePage from './Home/components/HomePage'

function App() {
  

  return (
    <>
      <Navbar></Navbar>

      <Routes>
        <Route path='/' element={<HomePage/>} />
      </Routes>

      <Footer></Footer>
    </>
  )
}

export default App
