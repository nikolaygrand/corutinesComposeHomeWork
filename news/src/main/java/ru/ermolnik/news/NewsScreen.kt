package ru.ermolnik.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NewsScreen(viewModel: NewsViewModel) {
    val state = viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        when (state.value) {
            is NewsState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }
            is NewsState.Error -> {
                Text(
                    text = (state.value as NewsState.Error).throwable.toString(),
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
            is NewsState.Content -> {
                LazyColumn {
                    items((state.value as NewsState.Content).news) { newsItem ->
                        Column(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = newsItem.title,
                                modifier = Modifier
                                    .wrapContentSize(),
                                textAlign = TextAlign.Center
                            )
                            newsItem.description?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier
                                        .wrapContentSize(),
                                    textAlign = TextAlign.Center
                                )
                            }
                            newsItem.full_description?.let {
                                Text(
                                    text = it,
                                    modifier = Modifier
                                        .wrapContentSize(),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Text(
                                text = newsItem.pubDate,
                                modifier = Modifier
                                    .wrapContentSize(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

            }
        }
    }
}
