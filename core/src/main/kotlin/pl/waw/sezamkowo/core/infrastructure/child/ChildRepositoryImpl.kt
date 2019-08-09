package pl.waw.sezamkowo.core.infrastructure.child

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import pl.waw.sezamkowo.core.domain.child.Child
import pl.waw.sezamkowo.core.domain.child.ChildRepository

interface MongoChildRepository : MongoRepository<Child, String>

@Repository
class ChildRepositoryImpl(private val repo: MongoChildRepository) : ChildRepository {
    override fun save(child: Child) {
        repo.save(child)
    }

    override fun deleteById(childId: String) {
        repo.deleteById(childId)
    }

    override fun findAll(): List<Child> {
        return repo.findAll()
    }
}