import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.okysoft.annictim.infra.api.model.response.Work
import com.okysoft.annictim.presentation.Paginator
import com.okysoft.annictim.presentation.WorkPaginator
import io.reactivex.Single
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

    private fun setupSuccessRequestCreator(): (Int) -> Single<List<Work>>  {
        val work: Work = mock(name = "Work")
        val requestCreator: ((Int) -> Single<List<Work>>) = { page ->
            Single.create<List<Work>> {
                val size = 20
                val response = mutableListOf<Work>()
                for (i in 1..size) {
                    response.add(work)
                }
                it.onSuccess(response)
            }
        }
        return requestCreator
    }

    private fun setupErrorRequestCreator(): (Int) -> Single<List<Work>>  {
        val requestCreator: ((Int) -> Single<List<Work>>) = { page ->
            Single.create<List<Work>> { it.onError(Throwable()) }
        }
        return requestCreator
    }

    @After
    fun tearDown() {

    }

    @Test
    fun pagination() {
        paginator = WorkPaginator(loadMore, refresh, setupSuccessRequestCreator())
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
        paginator = WorkPaginator(loadMore, refresh, setupSuccessRequestCreator())
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
        paginator = WorkPaginator(loadMore, refresh, setupErrorRequestCreator())
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