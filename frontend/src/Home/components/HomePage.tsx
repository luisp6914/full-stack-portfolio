import About from "./About";
import Contact from "./Contact";
import Home from "./Home";
import Projects from "./Projects";

const HomePage = () => {
    return (
        <>
            <div id="home"> <Home/> </div>
            <div id="about"> <About/> </div>
            <div id="projects" className="pb-5 pt-5"> <Projects/> </div>
            <div id="contact" className="pb-5 pt-5"> <Contact/> </div>
        </>
    );
}

export default HomePage;