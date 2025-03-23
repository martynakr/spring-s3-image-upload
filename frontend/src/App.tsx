import { FormEvent, useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import { createUser } from "./services";

function App() {
    const [file, setFile] = useState<File | null>(null);
    const [firstName, setFirstName] = useState<string>("");
    const [previewUrl, setPreviewUrl] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            const uploadedFile = e.target.files[0];
            setFile(uploadedFile);
            setPreviewUrl(URL.createObjectURL(uploadedFile));
        }
    };

    const handleSubmit = async (e: any) => {
        console.log(file);
        console.log(firstName, "first name");
        e.preventDefault();
        if (!file || !firstName) {
            console.log("no values");
            return;
        }
        const formData = new FormData();
        formData.append("firstName", firstName);
        if (file) {
            formData.append("file", file);
        }

        const res = await createUser(formData);
        console.log(res, "RES");
    };

    return (
        <>
            <form onSubmit={handleSubmit}>
                <input type="file" required onChange={handleChange} />
                {previewUrl && <img src={previewUrl} />}
                <input
                    type="text"
                    required
                    onChange={(e) => setFirstName(e.target.value)}
                />
                <button>Submit</button>
            </form>
        </>
    );
}

export default App;
