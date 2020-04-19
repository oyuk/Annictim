import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.okysoft.annictim.presentation.Paginator
import com.okysoft.domain.model.Work
import io.reactivex.processors.PublishProcessor
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.powermock.core.classloader.annotations.PrepareForTest
import java.util.concurrent.CountDownLatch

@PrepareForTest(Work::class)
class PaginatorTest {

    lateinit var paginator: Paginator<Work>
    lateinit var refresh: PublishProcessor<Unit>
    lateinit var loadMore: PublishProcessor<Unit>

    @Before
    fun setUp() {
        refresh = PublishProcessor.create()
        loadMore = PublishProcessor.create()
    }

    private fun setupSuccessRequestCreator(): ((Int , (Result<List<Work>>) -> Unit) -> Unit) {
        val work: Work = mock(name = "Work")
        val requestCreator: ((Int , (Result<List<Work>>) -> Unit) -> Unit) = { page, callback ->
            val size = 20
            val response = mutableListOf<Work>()
            for (i in 1..size) {
                response.add(work)
            }
            callback(Result.success(response))
        }
        return requestCreator
    }

    private fun setupErrorRequestCreator(): ((Int , (Result<List<Work>>) -> Unit) -> Unit) {
        val requestCreator: ((Int , (Result<List<Work>>) -> Unit) -> Unit) =  { page, callback ->
            callback(Result.failure(Throwable()))
        }
        return requestCreator
    }

    @After
    fun tearDown() {

    }

    @Test
    fun pagination() {
        paginator = Paginator<Work>(loadMore, refresh, setupSuccessRequestCreator())
        val countDownLatch = CountDownLatch(3)
        val subscriber = paginator.items
        val values = subscriber
            .doAfterNext {
                countDownLatch.countDown()
            }
            .test()
            .values()
        loadMore.onNext(Unit)
        Thread.sleep(600L)
        loadMore.onNext(Unit)
        Thread.sleep(600L)
        loadMore.onNext(Unit)
        Thread.sleep(600L)
        countDownLatch.await()
        assertThat(values.size).isEqualTo(3)
        assertThat(values[0].size).isEqualTo(20)
        assertThat(values[1].size).isEqualTo(40)
        assertThat(values[2].size).isEqualTo(60)
    }

    @Test
    fun refresh() {
        paginator = Paginator<Work>(loadMore, refresh, setupSuccessRequestCreator())
        val countDownLatch = CountDownLatch(3)
        val subscriber = paginator.items
        val values = subscriber
            .doAfterNext {
                countDownLatch.countDown()
            }
            .test()
            .values()
        refresh.onNext(Unit)
        refresh.onNext(Unit)
        refresh.onNext(Unit)
        countDownLatch.await()
        assertThat(values.size).isEqualTo(3)
        assertThat(values[0].size).isEqualTo(20)
        assertThat(values[1].size).isEqualTo(20)
        assertThat(values[2].size).isEqualTo(20)
    }

    @Test
    fun error() {
        paginator = Paginator<Work>(loadMore, refresh, setupErrorRequestCreator())
        val countDownLatch = CountDownLatch(3)
        val subscriber = paginator.error
        val values = subscriber
            .doAfterNext {
                countDownLatch.countDown()
            }
            .test()
            .values()
        refresh.onNext(Unit)
        refresh.onNext(Unit)
        refresh.onNext(Unit)
        countDownLatch.await()
        assertThat(values.size).isEqualTo(3)
        assertThat(values[0]).isInstanceOf(Throwable::class.java)
        assertThat(values[1]).isInstanceOf(Throwable::class.java)
        assertThat(values[2]).isInstanceOf(Throwable::class.java)
    }


}