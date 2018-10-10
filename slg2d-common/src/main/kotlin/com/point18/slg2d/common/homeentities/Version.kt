package com.point18.slg2d.common.homeentities

import javax.persistence.*

@Entity
@Table(name = "version")
data class VersionEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "db_version", nullable = false, length = 50)
    var dbVersion: String

) {
    constructor() : this(0, "")
}
