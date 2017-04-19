package za.co.entuit.medium.stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by RVukela on 2017/04/19.
 */
public class StreamPresenterTest {

    @Mock
    private StreamContract.View view;
    @Mock
    private StreamService streamService;
    private StreamPresenter presenter;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        presenter = new StreamPresenter(view);
    }

    @Test
    public void play() throws Exception {
        presenter.play("");
        verify(view).showProgressIndicator(true,0);
    }

    @Test
    public void stop() throws Exception {

    }

    @Test
    public void shutdown() throws Exception {

    }

}