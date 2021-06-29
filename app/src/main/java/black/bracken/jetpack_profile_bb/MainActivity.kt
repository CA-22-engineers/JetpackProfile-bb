package black.bracken.jetpack_profile_bb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import black.bracken.jetpack_profile_bb.ui.theme.JetpackprofilebbTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackprofilebbTheme {
                Surface {
                    MainLayout()
                }
            }
        }
    }
}

val COLOR_TWITTER_MAIN = Color(0xFF00ACEE)
val COLOR_TWITTER_SUB = Color.White

@Composable
fun MainLayout() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // createRefsForで置き換える
        val (
            header,
            iconSpacer,
            icon,
            socialButtonA,
            socialButtonB,
            followButton,
            displayName,
            twitterId,
            followedText,
            description,
            location,
            link,
            birthday,
            beginDay,
            followeeAndFollowers,
        ) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(header) {
                    centerHorizontallyTo(parent)
                    top.linkTo(parent.top)
                }
        )

        Spacer(
            modifier = Modifier
                .height(16.dp)
                .constrainAs(iconSpacer) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(icon.top)

                    centerHorizontallyTo(icon)
                }
        )

        ProfileIcon(
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(iconSpacer.bottom)
                    bottom.linkTo(header.bottom)

                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        SocialButton(
            modifier = Modifier
                .constrainAs(socialButtonA) {
                    centerVerticallyTo(followButton)
                    end.linkTo(socialButtonB.start, margin = 8.dp)
                }
        )

        SocialButton(
            modifier = Modifier
                .constrainAs(socialButtonB) {
                    centerVerticallyTo(followButton)
                    end.linkTo(followButton.start, margin = 8.dp)
                }
        )

        FollowButton(
            modifier = Modifier
                .constrainAs(followButton) {
                    top.linkTo(header.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        )

        Text(
            text = "くろわらび",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier
                .constrainAs(displayName) {
                    top.linkTo(icon.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        Text(
            text = "@black_bracken",
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(twitterId) {
                    top.linkTo(displayName.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        val followedByUser by remember { mutableStateOf(true) }
        if (followedByUser) {
            Text(
                text = " フォローされています ",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(20))
                    .background(Color.DarkGray)
                    .constrainAs(followedText) {
                        centerVerticallyTo(twitterId)
                        start.linkTo(twitterId.end, margin = 4.dp)
                    }
            )
        }

        Text(
            text = "B4 / 🐲 Kotlin / ⛄ Rust, Scala 3",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(twitterId.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        Location(
            modifier = Modifier
                .constrainAs(location) {
                    top.linkTo(description.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        Link(
            modifier = Modifier
                .constrainAs(link) {
                    centerVerticallyTo(location)
                    start.linkTo(location.end, margin = 8.dp)
                }
        )

        Birthday(
            modifier = Modifier
                .constrainAs(birthday) {
                    top.linkTo(location.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        BeginDay(
            modifier = Modifier
                .constrainAs(beginDay) {
                    top.linkTo(birthday.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        FolloweeAndFollowers(
            modifier = Modifier
                .constrainAs(followeeAndFollowers) {
                    top.linkTo(beginDay.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )
    }
}

@Composable
fun ProfileIcon(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(96.dp)
            .clip(CircleShape)
            .background(Color.Black),
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = null,
            modifier = Modifier
                .size(96.dp - 8.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun SocialButton(modifier: Modifier = Modifier) {
    IconButton(
        onClick = {},
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .border(1.dp, COLOR_TWITTER_MAIN, CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_ac_unit_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(COLOR_TWITTER_MAIN),
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun FollowButton(modifier: Modifier = Modifier) {
    var follows by remember { mutableStateOf(false) }

    Button(
        onClick = {
            follows = !follows
        },
        colors = ButtonDefaults
            .buttonColors(
                backgroundColor = if (follows) COLOR_TWITTER_MAIN else Color.Transparent,
                contentColor = if (follows) COLOR_TWITTER_SUB else COLOR_TWITTER_MAIN,
            ),
        modifier = modifier
            .size(
                // よしなに調整してくれるライブラリがあったはずだけど思い出せない
                width = if (follows) 112.dp else 128.dp,
                height = 32.dp
            )
            .clip(CircleShape)
            .border(
                width = if (follows) 0.dp else 1.dp,
                color = COLOR_TWITTER_MAIN,
                shape = CircleShape,
            ),
    ) {
        Text(if (follows) "フォロー中" else "フォローする")
    }
}

@Composable
fun Location(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_location_on_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = Modifier.size(16.dp),
        )

        Text(
            text = "bottom type",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}

@Composable
fun Link(modifier: Modifier) {
    val url = "https://github.com/blackbracken"

    val text = url.split("//").last()
    val annotatedUrlText = buildAnnotatedString {
        append(text)
        addStyle(
            style = SpanStyle(
                color = COLOR_TWITTER_MAIN,
                fontSize = 16.sp,
            ),
            start = 0,
            end = text.length,
        )
        addStringAnnotation(
            tag = "URL",
            annotation = url,
            start = 0,
            end = text.length,
        )
    }

    val uriHandler = LocalUriHandler.current
    ClickableText(
        text = annotatedUrlText,
        onClick = {
            val annotation = annotatedUrlText
                .getStringAnnotations("URL", it, it)
                .firstOrNull()
            if (annotation != null) {
                uriHandler.openUri(annotation.item)
            }
        },
        modifier = modifier
    )
}

@Composable
fun Birthday(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_cake_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = Modifier.size(16.dp),
        )

        Text(
            text = "誕生日: 2020年1月1日",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}

@Composable
fun BeginDay(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_calendar_today_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = Modifier.size(16.dp),
        )

        Text(
            text = "2017年12月からTwitterを利用しています",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}

@Composable
fun FolloweeAndFollowers(modifier: Modifier = Modifier) {
    val followeeCount = 190
    val followerCount = 212

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        val followeeCountText = buildAnnotatedString {
            val countText = followeeCount.toString()
            append(countText)
            addStyle(
                style = SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                ),
                start = 0,
                end = countText.length,
            )

            append(" フォロー")
            addStyle(
                style = SpanStyle(
                    color = Color.Gray
                ),
                start = countText.length,
                end = countText.length + 5,
            )
        }
        Text(
            text = followeeCountText
        )

        Spacer(modifier = Modifier.size(16.dp))

        val followerCountText = buildAnnotatedString {
            val countText = followerCount.toString()
            append(countText)
            addStyle(
                style = SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                ),
                start = 0,
                end = countText.length,
            )

            append(" フォロワー")
            addStyle(
                style = SpanStyle(
                    color = Color.Gray
                ),
                start = countText.length,
                end = countText.length + 6,
            )
        }
        Text(
            text = followerCountText
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetpackprofilebbTheme {
        Surface {
            MainLayout()
        }
    }
}
