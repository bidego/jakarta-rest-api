var url = "http://localhost:8080";

fetch(url + "/user/joako/?mensaje=holacapo", {
  method: "PATCH", // or 'PUT'
  headers: {
    "Content-Type": "application/json",
  },
})
  .then((res) => res.json())
  .catch((error) => console.error("Error:", error))
  .then((response) => console.log("Success:", response));
