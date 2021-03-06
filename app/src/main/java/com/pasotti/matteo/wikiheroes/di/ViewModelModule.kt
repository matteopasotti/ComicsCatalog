package com.pasotti.matteo.wikiheroes.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pasotti.matteo.wikiheroes.factory.AppViewModelFactory
import com.pasotti.matteo.wikiheroes.view.ui.home.HomeActivityViewModel
import com.pasotti.matteo.wikiheroes.view.ui.creator.CreatorDetailViewModel
import com.pasotti.matteo.wikiheroes.view.ui.detail.DetailActivityViewModel
import com.pasotti.matteo.wikiheroes.view.ui.detail_items.detail_comic.DetailItemViewModel
import com.pasotti.matteo.wikiheroes.view.ui.detail_items.detail_comic.more_comics.MoreGalleryFragmentViewModel
import com.pasotti.matteo.wikiheroes.view.ui.detail_items.detail_comic.more_info.MoreInfoViewModel
import com.pasotti.matteo.wikiheroes.view.ui.gallery.HorizontalGalleryViewModel
import com.pasotti.matteo.wikiheroes.view.ui.detail_items.detail_comic.DetailImageViewModel
import com.pasotti.matteo.wikiheroes.view.ui.home.comics.HomeComicsViewModel
import com.pasotti.matteo.wikiheroes.view.ui.home.desk.HomeDeskViewModel
import com.pasotti.matteo.wikiheroes.view.ui.person.PersonDetailActivityViewModel
import com.pasotti.matteo.wikiheroes.view.ui.person.comics.CreatorFragmentsViewModel
import com.pasotti.matteo.wikiheroes.view.ui.search.SearchActivityViewModel
import com.pasotti.matteo.wikiheroes.view.ui.seeall.SeeAllViewModel
import com.pasotti.matteo.wikiheroes.view.ui.seeall.series.SeriesSeeAllViewModel
import com.pasotti.matteo.wikiheroes.view.ui.splash.SplashActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    /**
     * Here we're providing entries to a multibound map, providing the value (ViewModel) and the key (ViewModelKey)
     */

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel::class)
    internal abstract fun bindHomeActivityViewModel(homeActivityViewModel: HomeActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    internal abstract fun bindDetailActivityViewModel(detailActivityViewModel: DetailActivityViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HorizontalGalleryViewModel::class)
    internal abstract fun bindHorizontalGalleryViewModel(horizontalGalleryViewModel: HorizontalGalleryViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoreGalleryFragmentViewModel::class)
    internal abstract fun bindMoreGalleryFragmentViewModel(moreGalleryFragmentViewModel: MoreGalleryFragmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoreInfoViewModel::class)
    internal abstract fun bindMoreInfoViewModel(moreInfoViewModel: MoreInfoViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SeriesSeeAllViewModel::class)
    internal abstract fun bindSeriesSeeAllViewModel( seeAllViewModel: SeriesSeeAllViewModel ) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SeeAllViewModel::class)
    internal abstract fun bindSeeAllViewModel( seeAllViewModel: SeeAllViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailItemViewModel::class)
    internal abstract fun bindDetailComicViewModel(detailItemViewModel: DetailItemViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatorDetailViewModel::class)
    internal abstract fun bindCreatorDetailViewModel( creatorDetailViewModel: CreatorDetailViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeComicsViewModel::class)
    internal abstract fun bindHomeComicsViewModel( homeComicsViewModel: HomeComicsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashActivityViewModel::class)
    internal abstract fun bindSplashActivityViewModel( splashActivityViewModel: SplashActivityViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeDeskViewModel::class)
    internal abstract fun bindHomeDeskViewModel( homeDeskViewModel: HomeDeskViewModel) :ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailImageViewModel::class)
    internal abstract fun bindDetailImageViewModel( detailImageViewModel: DetailImageViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchActivityViewModel::class)
    internal abstract fun bindSearchActivityViewModel( searchActivityViewModel: SearchActivityViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonDetailActivityViewModel::class)
    internal abstract fun bindPersonDetailActivityViewModel( personDetailActivityViewModel: PersonDetailActivityViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatorFragmentsViewModel::class)
    internal abstract fun bindCreatorFragmentViewModel(creatorFragmentsViewModel: CreatorFragmentsViewModel) : ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory) : ViewModelProvider.Factory
}