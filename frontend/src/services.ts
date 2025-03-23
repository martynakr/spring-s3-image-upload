export const createUser = async (data: any) => {
    try {
        const res = await fetch("http://localhost:8080/users", {
            method: "POST",
            body: data,
        });
        if (!res.ok) {
            throw new Error(await res.text());
        }

        return await res.json();
    } catch (e) {
        console.log(e, "EEEE");
        throw new Error("anotehr erro");
    }
};
