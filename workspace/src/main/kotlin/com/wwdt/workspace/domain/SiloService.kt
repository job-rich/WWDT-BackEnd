package com.wwdt.workspace.domain

interface SiloService {
    fun createSilo(createSiloVo: CreateSiloVo): Silo
    fun getSiloDetail(siloId: String): Silo
    fun getSiloList(userId: String): Array<Silo>
    fun updateSilo(updateSiloVo: UpdateSiloVo): Silo
    fun deleteSilo(siloId: String)
}
