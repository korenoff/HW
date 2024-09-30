import React, {
	useEffect,
	useState,
} from "react";
import axios from "axios";
import {
	FaEdit,
	FaEye,
	FaTrashAlt,
} from "react-icons/fa";
import { Link } from "react-router-dom";
import Search from "../common/Search";
import "../../App.module.scss";
import {postReq}from "../common/reqfun";
import {Button} from "@mui/material";

const DocumentsView = () => {
	const [documents, setDocuments] = useState([]);
	const [search, setSearch] = useState("");
	const [dateDeRegister, setDateDeRegister] = useState();
	useEffect(() => {
		loadDocuments();
	}, []);

	
	const loadDocuments = async () => {
		const result = await axios.get(
			"http://localhost:8080/SEDM/api/doc/findAll/",
			{
				validateStatus: () => {
					return true;
				},
			}
		);
		//console.log(result.data)
		//console.log(result.status)
		if (result.status === 200) {
			setDocuments(result.data);
		}
	};

	const handlerButtonRemove = (document) => {
        const setDateDeRegister = (value) => {
			setDateDeRegister(value);
		}    
		postReq("http://localhost:8080/SEDM/api/doc/remove", true, {id: document.id, docOutputNumber: `№${document.id}?:${document.docName}!:${document.docInputNumber}`})
                .then(() => {
                        let date = new Date().toLocaleDateString();
                        setDateDeRegister(date);
                        document.setDateDeRegistration(date);
                    }
                );
				loadDocuments();
	}	

	const handleDelete = async (id) => {
		await axios.delete(
			`http://localhost:8080/SEDM/api/doc/delete/${id}`
		);
		loadDocuments();
	};

	
	return (
		<section>
			
			<Search
				search={search}
				setSearch={setSearch}
			/>
			<table className="table table-bordered table-hover shadow">
				<thead>
					<tr className="text-center">
						<th>ID</th>
						<th>ID in DB</th>
						<th>DocumentName</th>
						<th>Author</th>
						<th>ImputDate</th>
						<th>OutputDate</th>
						<th>InputNumber</th>
						<th>OutputNumber</th>
						<th colSpan="2">Просмотр версий/Снять с учета </th>
					</tr>
				</thead>

				<tbody className="text-center">
					{documents
						.filter((st) =>
							st.docName
								//.toLowerCase()
								.includes(search)
						)
						.map((document, index) => (
							<tr key={document.id}>
								<th scope="row" key={index}>
									{index + 1}
								</th>

								<td className={document.docOutputNumber ? "text-danger" : "text-success"}>{document.id}</td>
								<td className={document.docOutputNumber ? "text-danger" : "text-success"}>{document.docName}</td>
								<td className={document.docOutputNumber ? "text-danger" : "text-success"}>{document.author}</td>
								<td className={document.docOutputNumber ? "text-danger" : "text-success"}>{document.dateInit}</td>
								
								<td className={document.docOutputNumber ? "text-danger" : "text-success"}>{document.dateDeRegistration}</td>
								<td className={document.docOutputNumber ? "text-danger" : "text-success"}>{document.docInputNumber}</td>
								<td className={document.docOutputNumber ? "text-danger" : "text-success"}>{document.docOutputNumber}</td>
								
								<td className="mx-2">
									<Link
										to={`/document-profile/${document.id}`}
										className="btn btn-info">
										<FaEye />
									</Link>
								</td>
								{/* <td className="mx-2">
									<Link
										to={`/edit-document/${document.id}`}
										className="btn btn-warning">
										<FaEdit />
									</Link>
								</td> */}
								<td className="mx-1">
									<Button
										// className="btn btn-danger"
										
										onClick={
									 		() => handlerButtonRemove(document)
										}
                                    	sx={{backgroundColor: "#b60a1c"}}>
										<FaTrashAlt />
									</Button>
								</td>
							</tr>
						))}
				</tbody>
			</table>
			
		</section>
	);
};

export default DocumentsView;
