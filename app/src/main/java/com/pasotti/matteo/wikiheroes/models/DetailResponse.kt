package com.pasotti.matteo.wikiheroes.models

data class DetailResponse(val code: Int, val status: String, val copyright: String,
                          val attributionText: String, val attributionHTML: String,
                          val etag: String, val data: CharacterDetailData)