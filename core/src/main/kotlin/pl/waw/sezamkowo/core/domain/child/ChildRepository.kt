package pl.waw.sezamkowo.core.domain.child

interface ChildRepository {
    fun save(child: Child)
    fun deleteById(childId: String)
    fun findAll(): List<Child>
}