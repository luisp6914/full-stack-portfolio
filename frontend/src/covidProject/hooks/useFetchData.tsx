import axios from "axios";
import { useEffect, useState } from "react"

const useFetchData = () => {
    //Fetched data 
    const [vaccines, setVaccines] = useState([]);
    const [patients, setPatients] = useState([]);

    const allVaccinesURL = "http://localhost:8080/getAllVaccines";
    const allPatientsURL = "http://localhost:8080/getAllPatients"

    //Getting list of vaccines 
    const fetchVaccines = async () => {
        try {
            const response = await axios.get(allVaccinesURL);
            setVaccines(response.data);
        } catch (error) {
            console.error("Error getting Vaccines [useFetchData file", error)
        }
    }

    const fetchPatients = async () => {
        try {
            const response = await axios.get(allPatientsURL);
            setPatients(response.data);
        } catch (error) {
            console.error("Error fetching patients [useFetchData.tsx file]", error);
        }
    }

    useEffect(() => {
        fetchVaccines();
        fetchPatients();
    },[]);

    return {vaccines, fetchVaccines, patients, fetchPatients};
}

export default useFetchData;