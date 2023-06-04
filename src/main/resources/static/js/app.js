const onSubmit = () => {
    let form = document.getElementById("form");
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let dod = document.getElementById("dod").value;
    axios.post("/api/v1/student", {
        name,
        email,
        dod
    })
    .then(() => {
        alert("Succes ✅");
    })
    .catch((e) => {
        alert("Can not send request❗: " + e.response.data.message);
        console.log(e);
    }).finally(() => {
        form.querySelectorAll("input").forEach(item => {
            item.value = "";
        })
    })
}