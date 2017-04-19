package za.co.entuit.medium;

import za.co.entuit.medium.data.ArticlesRepository;

/**
 * Created by RVukela on 2017/04/19.
 */

public class Injection {
    public static ArticlesRepository provideArticlesRepository(){
        return new FakeArticlesRepositoryImpl();
    }
}
