import Nav from 'react-bootstrap/Nav';
import {Container, Navbar} from "react-bootstrap";
import {useState} from "react";

const AppHeader = () => {
    const [students, setStudents] = useState();
    return (
        <Navbar bg="primary" variant="dark">
            <Container>
                <Navbar.Brand href="#home">Student Record</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="#students">Students</Nav.Link>
                    <Nav.Link href="#create-students">Create Student</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    );
}

export default AppHeader;