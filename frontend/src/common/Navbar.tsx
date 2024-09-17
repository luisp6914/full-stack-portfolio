import { Icon } from '@iconify/react';
import { Link } from 'react-router-dom';
//PascalCasing for components
const Navbar = () => {
    return (
        <nav className="navbar navbar-expand-lg bg-light">
            <div className="container-fluid">
                {/*Logo */}
                <Link className="navbar-brand" to={"/"}>
                    <span>
                        <Icon icon="tabler:circle-letter-l" width="48" height="48" />
                    </span>
                </Link>

                {/*Nav drop down tab */}
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbaMarkup" aria-controls="navbaMarkup" aria-expanded="false" aria-label="Toggle navigation" >
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id='navbaMarkup'>
                    <div className='navbar-nav'>
                        {/*Home Tab */}
                        <Link to="/" className="nav-link"> Home </Link>

                        {/**About Tab */}
                        <Link to="/about" className="nav-link">About</Link>

                        {/**Projects Tab */}
                        <Link to="/projects" className="nav-link">Projects</Link>

                        {/**Contact Tab */}
                        <Link to="/contact" className="nav-link">Contact</Link>
                    </div>
                    
                </div>


            </div>
        </nav>
    );
}

export default Navbar;