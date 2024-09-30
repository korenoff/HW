import React from 'react';
import {Box, Button, Grid, TextField, Typography} from "@mui/material";

//import {observer} from "mobx-react-lite";
import {useState} from "react";
//import useStore from "../utils/useStore";


const CreateDoc = () => {
    const [file, setFile] = useState(null);
    const [docName, setDocName] = useState("");
    const [inputNumber, setInputNumber] = useState("");
	const [inputAuthor, setInputAuthor] = useState("");
    //const {docsStore} = useStore();
    
	const onFileChange = (event) => {
        setFile(event.target.files[0]);
    }
    
	const updateDocName = (event) => {
        const {value} = event.target;
        setDocName(value);
    }
	
	const updateInputAuthor = (event) => {
        const {value} = event.target;
        setInputAuthor(value);
    }

	const updateInputNumber = (event) => {
        const {value} = event.target;
        setInputNumber(value);
    }

    const uploadFile = (event) => {
        event.preventDefault();
        setDocName("");
        setInputNumber("");
        setFile(null);
        if (file === null || file === undefined) {
            return;
        }
        let data = new FormData();
        data.append('fileData', file);
        data.append('author', inputAuthor);
        data.append('docName', docName);
        data.append('inputNumber', inputNumber);
		
        fetch("http://localhost:8080/SEDM/api/doc/create", 
		{
			method: 'POST',
            body: data, 
        })
		//navigate("/view-documents");
    }

    return (
        <div>
            <Box
                sx={{
                    mt: 3,
                    mr: 3,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'flex-start',
                    backgroundColor: 'navbar-dark',
                    borderRadius: 5,
                }}
            > <Grid container sx={{ml: 2, mt: 2}}>
                <Grid sx={{width: 400, height: 70}} alignItems='flex-start'>
                    <Typography variant='h4' align='center'>
                        Создание нового документа
                    </Typography>
                </Grid>
                <Grid sx={{width: 400, minHeight: 330}}>
                    <Grid>
                        <Typography align='left' variant='h4'>
                            Введите название документа:
                        </Typography>
                    </Grid>
                    <TextField
                        margin="normal"
                        required={true}
                        label='Название'
                        name="name"
                        onChange={updateDocName}
                        sx={{width: 360, mr: 4, mt: 2, backgroundColor: '#FFFFFF'}}
                    />
					<Grid>
                        <Typography align='left' variant='h4'>
                            Введите автора:
                        </Typography>
                    </Grid>
                    <TextField
                        margin="normal"
                        required={true}
                        label='Название'
                        name="name"
                        onChange={updateInputAuthor}
                        sx={{width: 360, mr: 4, mt: 2, backgroundColor: '#FFFFFF'}}
                    />
                    <Grid>
                        <Typography align='left' variant='h4'>
                            Введите входящий номер:
                        </Typography>
                        <TextField
                            margin="normal"
                            required={true}
                            label='Входящий номер'
                            name="name"
                            onChange={updateInputNumber}
                            sx={{width: 360, mr: 4, mt: 2, backgroundColor: '#FFFFFF'}}
                        />
                    </Grid>
                    <Grid container sx={{mt: 1, ml: 1}}>
                        <input onChange={onFileChange} multiple type="file"/>
                    </Grid>
                    <Grid container sx={{mt: 1, mb: 1}}>
                        <Button
                            type="submit"
                            inputtype="file"
                            variant="contained"
                            onClick={uploadFile}
                            sx={{ml: 2, mt: 2, width: 340}}
                        >Создать документ
                        </Button>
                    </Grid>
                </Grid>
            </Grid>
            </Box>
        </div>
    )
        ;
}
export default CreateDoc;