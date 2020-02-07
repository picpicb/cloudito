swagger: "2.0"
info:
    description: "PDS Cloudito"
version: "1.0.0"
title: "PDS Cloudito"
host: "Cloudito"
basePath: "/v1"
schemes:
    - "https"
produces:
    - "application/json"
paths:
    "/map/course/{customerid}"
:
    parameters:
        - name
: "customerid"
        in
: "query"
        type: "string"
        required: true
        description: "id utilisateur"
    get:
        summary: "récupére le parcours d'un client"
        tags:
            - "Courses"
        responses:
            200
:
                description: "la description"
                schema:
                    $ref: '#'
            404
:
                description: "le client n'existe pas"
                schema:
                    $ref: '#'
    "/map/course/{a}/{b}"
:
    parameters:
        - name
: "a"
        in
: "query"
        type: "string"
        required: true
        description: "debut"
        - name
: "b"
        in
: "query"
        type: "string"
        required: true
        description: "fin"
    get:
        summary: "récupére le parcours"
        tags:
            - "Courses"
        responses:
            200
:
                description: "la description"
                schema:
                    $ref: '#'
            404
:
                description: "le client n'existe pas"
                schema:
                    $ref: '#'
    "/map/nodes"
:
    get:
        summary: "Map avec noeuds"
        tags:
            - "Map"
    responses:
        200
:
            description: "OK"
            schema:
                $ref: '#'
        400
:
            description: "PAS OK"
    "/map/stores"
:
    get:
        summary: "Map avec magasins"
        tags:
            - "Map"
    responses:
        200
:
            description: "OK"
            schema:
                $ref: '#'
        400
:
            description: "PAS OK"
    "/mapRaw/"
:
    get:
        summary: "Map entière"
        tags:
    - "Map"
    responses:
        200
:
            description: "OK"
            schema:
                $ref: '#'
        400
:
    description: "PAS OK"

