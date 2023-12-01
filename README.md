
# README - Registro de Usuarios API
## Descripción
Este documento proporciona información sobre cómo utilizar el punto final de registro de usuarios de la API, ubicado en http://localhost:8080/api/users/register. 
También se proporciona un ejemplo de solicitud para realizar pruebas.

## Punto Final de la API
  URL: http://localhost:8080/api/users/register
  Método: POST
## Ejemplo de Solicitud
```
{
    "name": "Juan Rodriguez3",
    "email": "juan@rodriguez.org",
    "password": "a12",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1232",
            "contryCode": "57"
        }
    ]
}
```
## Token
La respuesta de la API incluye un Token JSON Web (JWT) después de un registro exitoso.

## Configuración de Contraseña y Correo Electrónico
Los requisitos de formato de contraseña y correo electrónico son configurables y se definen mediante expresiones regulares en el archivo application.yaml.

## Validacion de Contraseña
La expresión regular para la contraseña es la siguiente:
```
custom:
  passwordRegex: "^(?=.*[a-zA-Z])(?=.*\\d).+$"
```
Esto significa que la contraseña debe contener al menos un carácter y al menos un número.

## Validacion de Correo Electrónico
La expresión regular para el formato de correo electrónico es la siguiente:

```
custom:
  emailRegex: "^[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$"
```

Esto asegura que el correo electrónico siga el formato estándar nombredeusuario@dominio.algo.

## Configuración Personalizada
Si desea ajustar las expresiones regulares, puede modificar los valores en el archivo application.yaml bajo la sección custom.
Asegúrese de reiniciar la aplicación después de realizar cambios para que tengan efecto.
## Diagrama
![DIAGRAMA_SMARTJOB](https://github.com/CRISTIAN1993UCV/SMART_JOB_RETO_TECNICO/assets/145594124/81e17ee7-8dd5-49d3-9655-18f9264f1801)

