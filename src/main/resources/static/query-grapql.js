async function fetchAlimentacionesById() {
    const query = `query{
            mostrarAlimentacionesPorId(id:"1"){
            id
            descripcion
            dosis
  }
}`;

    const response = await fetch('/graphql', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ query }),
    });

    const result = await response.json();

    if (response.ok) {
        return result.data.mostrarAlimentacionesPorId;
    } else {
        console.error('Error fetching data:', result.errors);
        return [];
    }
}

// Función para mostrar los datos en la página
function displayAlimentaciones(alimentaciones) {
    const tableBody = document.getElementById('alimentaciones-table-body');
    alimentaciones.forEach(alimentacion => {
        const row = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = alimentacion.id;
        row.appendChild(idCell);

        const descripcionCell = document.createElement('td');
        descripcionCell.textContent = alimentacion.descripcion;
        row.appendChild(descripcionCell);

        const dosisCell = document.createElement('td');
        dosisCell.textContent = alimentacion.dosis;
        row.appendChild(dosisCell);

        tableBody.appendChild(row);
    });
}
