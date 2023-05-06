import {Container} from "react-bootstrap";
import {useEffect, useState} from "react";
import StudentRow from "./StudentRow";

const StudentLists = ({selectStudent}) => {
    const [students, setStudents] = useState([]);

    const fetchStudents = async () => {
        const response = await fetch(
            "http://127.0.0.1:8080/api/students",
            {
                method: 'GET'
            }
        );
        const students = await response.json();
        console.log("RESPONSE : " + JSON.stringify(students));
        setStudents(students);
    };

    useEffect(() => {
        fetchStudents().catch(reason => console.log(reason))
    }, []);

    return (
        <Container className="row">
            <div className="row mb-2">
                <h5 className="themeFontColor text-center">
                    List students
                </h5>
            </div>
            <table className="table table-hover">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Age</th>
                    <th>Name</th>
                </tr>
                </thead>
                <tbody>
                {students.map(s => (
                    <StudentRow key={s.id} student={s} selectStudent={selectStudent}/>
                ))}
                </tbody>
            </table>
        </Container>
    );
}

export default StudentLists;