function submit_recording(name, data) {
    const form_data = new FormData();
    form_data.append(name, data);
    const request = new XMLHttpRequest();
    request.open("POST", "tst0.herokuapp.com/upload");
    request.send(form_data);
    return ("submit_recording function will immediately return");
}