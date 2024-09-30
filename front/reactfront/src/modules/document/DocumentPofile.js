import React, {
	useEffect,
	useState,
} from "react";

import { useParams } from "react-router-dom";
import axios from "axios";
import Search from "../common/Search";
import {Button, Grid} from "@mui/material";
import {useNavigate} from "react-router";
import { Link } from "react-router-dom";
import "./AddVersionDocument";

const DocumentPofile = () => {
	const { id } = useParams();
	const navigate = useNavigate();
	const [document, setDocument] = useState([]);
	const [search, setSearch] = useState("");
	
	useEffect(() => {
		loadDocument();
	}, []);
	
	function Back() {
        navigate("/view-documents");
    }

	const loadDocument = async () => {
		const result = await axios.get(
			`http://localhost:8080/SEDM/api/doc/versiondoc/${id}`,{
				validateStatus: () => {
					return true;
				},
			}
		);
		setDocument(result.data);

	};

	const downloadFile = (d_id) => {
        fetch("http://localhost:8080/SEDM/api/doc/versiondocfile" + "/" + d_id)
            .then(response => {
                response.blob().then(blob => {
                    const url = window.URL.createObjectURL(blob);
                    const link = window.document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', d_id);
                    window.document.body.appendChild(link);
                    link.click();
                    link.parentNode.removeChild(link);
                });
            });
    }

	return (
		
	<section>
		<Search
				search={search}
				setSearch={setSearch}
			/>
		 	<Button sx={{ml: 0}} type="submit" variant="contained" onClick={Back}>Назад</Button>
		 					<div className="card-body text-center">
							 	
		 						<h5 className="my-3">
		 							Document ID {id} versions
		 						</h5>
								<Grid>

										<Link
											to={`/AddVersionDocument/${id}`}
											className="btn btn-info">
											Создать новую версию документа
										</Link>
								</Grid>
								<table className="table table-bordered table-hover shadow">
									<thead>
										<tr className="text-center">
											<th>ID</th>
											<th>ID in DB</th>
											<th>FileName</th>
											<th>Author</th>
											<th colSpan="3"></th>
										</tr>
									</thead>
									<tbody className="text-center">
										{document
										.filter((st) =>
											st.fileName
												.toLowerCase()
												.includes(search)
										)
										.map((document, index) => (
											<tr key={document.id}>
												<th scope="row" key={index}>
													{index + 1}
												</th>
												<td>{document.id}</td>
												
												<td>{document.fileName}</td>
												<td>{document.author}</td>
												{/* <td 
													button type="button" class="btn btn-success btn-sm">Download
												</td> */}

												<Button
													type="submit"
													variant="contained"
													value="{$document_id}"
													onClick={
														() => downloadFile(`${document.id}`)
													}
													sx={{mb: 1, mt: 2}}
													>Скачать файл
												</Button>
											
											</tr>
										))}
									</tbody>
								</table>
							</div>
						
		</section>
	);
};

export default DocumentPofile;
