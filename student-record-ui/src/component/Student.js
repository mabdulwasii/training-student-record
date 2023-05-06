import defaultPhoto from "../helpers/defaultPhoto";

const Student = ({student}) => {
    return (
        <div className="row">
            <div className="col-6">
                <div className="row">
                    <img
                        className="img-fluid"
                        src={
                            student.photo ? `./houseImages/${student.photo}.jpeg` : defaultPhoto
                        }
                        alt="Student pic"
                    />
                </div>
            </div>
            <div className="col-6">
                <div className="row mt-2">
                    <h5 className="col-12">{student.id}</h5>
                </div>
                <div className="row">
                    <h3 className="col-12">{student.age}</h3>
                </div>
                <div className="row">
                    <h2 className="themeFontColor col-12">
                        {student.name}
                    </h2>
                </div>
                <div className="row">
                    <div className="col-12 mt-3">{student.description}</div>
                </div>
            </div>
        </div>
    );
}

export default Student;