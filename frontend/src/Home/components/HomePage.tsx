import About from "./About";
import Contact from "./Contact";
import Home from "./Home";
import Projects from "./Projects";

const HomePage = () => {
    return (
        <>
            <div id="home">
                <Home></Home>
            </div>
            <div id="about"> <About></About></div>
            <div id="projects"> <Projects></Projects ></div>
            <div id="contact"> <Contact></Contact> </div>
        </>
    );
}

export default HomePage;