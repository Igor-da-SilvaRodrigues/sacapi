# Usuario
Operações com os usuarios
## \usuario
### GET
Retorna todos os usuarios
```
# exemplo de retorno em uma lista de objetos
[
    {
        "matricula":"abc",
        "nome":"Fulano",
        "usuarioAdm": True,
    },
    {
        "matricula":"def",
        "nome":"Cicrano",
        "usuarioAdm": False,
    }
]
```
#### retorno
|código|razão|
-------|------
`OK`   |sucesso


## \usuario\\{idUsuario}
### GET
Retorna informações de um único usuário, recebe a matrícula do usuario como parâmetro de URL
```
# exemplo de retorno
{
    "matricula":"abc",
    "nome":"Fulano",
    "usuarioAdm": True,
}

```
#### retorno
|código    |razão|
-----------|------
`OK`       |sucesso
`NOT_FOUND`|usuario não encontrado

# Setor
## \setor
### GET
Retorna todos os setores, em uma lista de objetos
```
# exemplo de retorno
[
    {
        "setor":"nome_do_setor",
        "email":"fulano@gmail.com",
    },
    {
        "setor":"nome_do_setor",
        "email":"cicrano@gmail.com",
    },
]

```
#### retorno
|código|rasão|
-------|------
`OK`   |sucesso

# Historico
## \historico
### GET
Retorna todos os históricos de um chamado como uma lista de objetos
#### parametros
|parametro | tipo  | descrição|
|----------|-------|----------|
|protocolo | string| numero de protocolo do chamado

```
# exemplo de retorno
[
    {
        "status":"1",
        "parecer":"um parecer qualquer",
        "dataMod":"03-01-1969"
        "setor":"nome do Setor"

    },
    {
        "status":"0",
        "parecer":"um parecer qualquer",
        "dataMod":"13-02-1969"
        "setor":"nome do outro Setor"
    }
]
```
#### retorno
|código    |rasão
-----------|------
`OK`       |sucesso
`NOT_FOUND`|chamado não encontrado


# Chamado
## \chamado
### POST
cria um chamado
```
#exemplo de entrada de corpo
{
    "idTipoChamado":"cartão",
    "idMotivo":"2a via",
    "justificativa":"uma justificativa",
    "dataAbertura":"24-12-2012",
    "idDiscente":"matricula",
}
```
#### retorno
|código|rasão|
-------|------
`OK`   |sucesso
`BAD_REQUEST`|entrada incorreta

### PUT
atualiza um chamado
```
# exemplo de entrada de corpo
{
    "idUsuario":"matricula",
    "protocolo":"protocolo do chamado",
    "status":2,
    "parecer":"atualizando parecer",
    "idSetor":"adm"
}
```
#### retorno
|código|razão|
-------|------
`OK`   |sucesso
`BAD_REQUEST`|entrada incorreta
`NOT_FOUND`|usuario, chamado ou setor não encontrados
`UNAUTHORIZED`|o usuario não é um administrador

### GET
retorna todos os chamados como uma lista de objetos
Se o usuario for um aluno, retorna os chamados que pertencem aquela matrícula, assim como o respectivo histórico inicial. Caso o usuario for administrador, retorna informação sobre todos os chamados
```
# exemplo de entrada
{
    "idUsuario":"matricula",
}
```
   
```
# exemplo de retorno de administrador
[
    {
        "protocolo":"codigodeprotocolo",
        "dataAbertura":"2008-08-08,
        "status":1,
        "prioridade":2,
    },...
]
```
```
#exemplo de retorno de aluno
[
    {
        "inicial":{
            "tipoChamado":"um tipo ai",
            "motivo":"o motivo",
            "justificativa": "blabla",
            "dataAbertura":"2022-03-23",
        },
        "final":{
            "parecer":"um parecer final",
            "dataFehamento":"2033-03-23",

        }
    },...
]
```
#### retorno
|código|razão|
-------|------
`OK`   |sucesso
`BAD_REQUEST`|entrada incorreta
`NOT_FOUND`|usuario não encontrado



## \chamado\\{idChamado}
### GET
Retorna as informações sobre um chamado específico

```
# exemplo de retorno
{
    "status":1,
    "parecer":"o parecer atual",
    "setor":"o nome do setor",
    "tipoChamado":"o nome do tipo",
    "motivo":"um dos motivos",
    "justificativa":"blabla",
    "dataAbertura":"2024-03-23",
    "matriculaAluno":"k0e230e3",
    "nomeAluno":"fulano",
}
```
#### retorno
|código|razão|
-------|------
`OK`   |sucesso
`BAD_REQUEST`|entrada incorreta
`NOT_FOUND`|chamado não encontrado


# TipoChamado
Operações sobre tipos de chamado
## \tipochamado
### POST
Cria um novo tipo de chamado
```
# exemplo de retorno
"tipo":"nome do tipo",
"motivos":["motivo1","m2","m3"...],
"prioridade":0
```
#### retorno
|código|razão|
-------|------
`OK`   |sucesso
`BAD_REQUEST`|entrada incorreta

### GET
Retorna todos os tipos de chamados como uma lista de objetos
```
# exemplo de retorno
[
    {
        "tipo":"nome do tipo",
        "prioridade":2,
        "motivos":["m1","m2","m3"...],
        "arquivado":False
    }
]
```

## \tipoChamado\\{idTipoChamado}
### DELETE
Arquiva um tipo de chamado para que ele não esteja mais disponível para escolha
#### retorno
|código|razão|
-------|------
`OK`   |sucesso
`NOT_FOUND`|tipo de chamado não encontrado
`BAD_REQUEST`|entrada incorreta

