async function AddAlimentacion() {
    try {
        const description = document.getElementById('desc').value;
        const dosis = document.getElementById('dosis').value;

        console.log(dosis)

        const query = `mutation{
                       createAlimentacion(inputAlimentacion:{
                       descripcion: "${description}",
                       dosis: ${dosis}
                        })
                        }`;

        const response = await fetch('/graphql', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ query }),
        });

        const data = await response.json();
        console.log('alimentacion añadida corretamente ');
    }catch (e){
        console.log("no se envió la peticion" + e)
    }

}

async function EliminarAlimentacion(id) {
    try {
        //const id = document.getElementById('id').value;

        //console.log(id);

        console.log(id)

        const query = `mutation{
  
  deleteAlimentacionById(alimentacionId:"${id}")
}`;

        const response = await fetch('/graphql', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ query }),
        });

        const data = await response.json();
        console.log('alimentacion eliminada corretamente ');
        alert(data)
    }catch (e){
        console.log("no se envió la peticion" + e)
    }

}

