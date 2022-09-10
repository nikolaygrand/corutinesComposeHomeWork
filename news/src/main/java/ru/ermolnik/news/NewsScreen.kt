package ru.ermolnik.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

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
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Прозошла ошибка попробуйте ещё раз",
                        modifier = Modifier
                            .wrapContentSize(),
                        textAlign = TextAlign.Center
                    )
                    Button(onClick = { viewModel.refresh() }) {
                        Text(text = "Обновить")
                    }
                }

            }
            is NewsState.Content-> {
                SwipeRefresh(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberSwipeRefreshState(false),
                    onRefresh = { viewModel.refresh() },
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val newsFillList = (state.value as? NewsState.Content)?.news ?: emptyList()
                        itemsIndexed(newsFillList) { index, newsItem ->
                            Column {
                                Text(
                                    text = newsItem.title,
                                    modifier = Modifier
                                        .wrapContentHeight(),
                                    style = MaterialTheme.typography.h5,
                                    textAlign = TextAlign.Start
                                )
                                newsItem.description?.let {
                                    Text(
                                        text = it,
                                        modifier = Modifier
                                            .wrapContentHeight(),
                                        style = MaterialTheme.typography.subtitle2,
                                        textAlign = TextAlign.Justify
                                    )
                                }
                                Text(
                                    text = newsItem.pubDate,
                                    modifier = Modifier
                                        .wrapContentHeight(),
                                    style = MaterialTheme.typography.caption,
                                    textAlign = TextAlign.Right
                                )

                            }
                            if (index < newsFillList.lastIndex)
                                Divider(color = Color.Black,
                                    thickness = 1.dp,
                                    modifier = Modifier.padding(vertical = 12.dp)
                                )
                        }
                    }
                }
            }
        }
    }
}
