async function getData(url = "") {
    // Default options are marked with *
    const response = await fetch(url, {
      method: "GET", // *GET, POST, PUT, DELETE, etc.
      mode: "cors", // no-cors, *cors, same-origin
      cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
      credentials: "same-origin", // include, *same-origin, omit
      headers: {
        "Content-Type": "application/json",
      },
      redirect: "follow", // manual, *follow, error
      referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    });
    console.log('before json');
    return response.json(); // parses JSON response into native JavaScript objects
  }
  
  document.getElementById('get-clients').addEventListener('click', async function() {
    const data = await getData('http://localhost:9090/api/v1/client');
    let tableHtml = '<table border="1"><tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th></tr>';
    data.forEach(client => {
        tableHtml += `<tr><td>${client.id}</td><td>${client.name}</td><td>${client.email}</td><td>${client.phone}</td></tr>`;
    });
    tableHtml += '</table>';
    document.getElementById('client-table').innerHTML = tableHtml;
});