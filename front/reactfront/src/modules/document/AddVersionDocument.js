import {useState} from "react";
import {Box, Button, Grid, TextField, Typography} from "@mui/material";
import React from "react";

import { useParams } from "react-router-dom";
const CreateVersionDoc = () => {
    const { id } = useParams();
    const [file, setFile] = useState(null);
    const onFileChange = (event) => {
        setFile(event.target.files[0]);
    }
    const [inputAuthor, setInputAuthor] = useState("");
    const updateInputAuthor = (event) => {
        const {value} = event.target;
        setInputAuthor(value);
    }
    const uploadFile = (event) => {
        event.preventDefault();

        if (file === null || file === undefined) {
            return;
        }
        let data = new FormData();
        data.append('fileData', file);
        data.append('id', id);
        data.append('author', inputAuthor)
        //data.append('author', docsStore.login);

        fetch("http://localhost:8080/SEDM/api/doc/newversiondoc", {
            method: 'POST',
            body: data
        }).then(() => {
            //docsStore.getVersionsDoc();
            setFile(null);
        });
        //docsStore.setCheckCreation(false);
    }

    return (
        <div>
            <Box
            > <Grid container sx={{ml: 2, mt: 2}}>
                <Grid sx={{width: 400, height: 70}} alignItems='flex-start'>
                    <Typography variant='h5' align='left'>
                        Создание новой версии
                    </Typography>
                </Grid>
                <Grid>
                        <Typography align='center' variant='h6'>
                            Введите автора: 
                        </Typography>
                    </Grid>
                    <TextField
                        margin="normal"
                        required={true}
                        label='Название'
                        name="name"
                        onChange={updateInputAuthor}
                    />
                <Grid>
                    <Grid container sx={{mt: -1, ml: 1}}>
                        <input onChange={onFileChange} multiple type="file"/>
                    </Grid>
                    <Grid container sx={{}}>
                        <Button
                            type="submit"
                            inputtype="file"
                            variant="contained"
                            onClick={uploadFile}
                            sx={{ml: 3, mt: 1, width: '80%'}}
                        >Создать документ
                        </Button>
                    </Grid>
                </Grid>
            </Grid>
            </Box>
        </div>
    );
}
export default CreateVersionDoc;