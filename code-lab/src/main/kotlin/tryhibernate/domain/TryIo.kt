package tryhibernate.domain

import tryhibernate.persistence.Domain4Lab
import javax.persistence.*

@Entity
@Table(name = "tryio")
class TryIo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    override var id: Long,

    @Column(name = "name", nullable = false, length = 150)
    var name: String
) : Domain4Lab<TryIo> {

    constructor() : this(0, "")

}