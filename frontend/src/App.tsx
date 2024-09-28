import { Route, Routes } from 'react-router-dom'
import Navbar from './common/Navbar'
import Footer from './common/Footer'
import HomePage from './Home/components/HomePage'
import Portal from './covidProject/Portal'
import HealthRecords from './covidProject/HealthRecords'

function App() {
  

  return (
    <>
      <Navbar></Navbar>

      <Routes>
        <Route path='/' element={<HomePage/>} />
        <Route path='/home' element={<HomePage/>} />
        <Route path='/about' element={<HomePage/>} />
        <Route path='/projects' element={<HomePage/>} />
        <Route path='/contact' element={<HomePage/>} />
        <Route path='/covid-project' element={<Portal/>} />
        <Route path='/covid-project/patients' element={<HealthRecords/>} />
        <Route path='/covid-project/vaccines' element={<HealthRecords/>} />
      </Routes>

      <Footer></Footer>
    </>
  )
}

export default App
