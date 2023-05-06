const StudentRow = ({student, selectStudent}) => {
    return (
        <tr onClick={() => selectStudent(student)}>
            <td>{student.id}</td>
            <td>{student.age}</td>
            <td>{student.name}</td>
        </tr>
    );
};

export default StudentRow;