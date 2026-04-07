/*
 Google Material Symbols - Rounded.
 License Apache 2.0 (https://github.com/google/material-design-icons/blob/f2749daed485839fb131415339546549d302bebc/LICENSE)
*/

@file:Suppress("ktlint:compose:param-order-check")

package vn.id.tozydev.lucidabyss.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.SVGFillType
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.dom.svg.ViewBox
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.svg.SVGSVGElement

@Composable
fun LightModeIcon(modifier: Modifier = Modifier) {
    IconWrapper(modifier) {
        Path {
            d(
                "M565-395q35-35 35-85t-35-85q-35-35-85-35t-85 35q-35 35-35 85t35 85q35 35 85 35t85-35Zm-226.5 56.5Q280-397 280-480t58.5-141.5Q397-680 480-680t141.5 58.5Q680-563 680-480t-58.5 141.5Q563-280 480-280t-141.5-58.5ZM80-440q-17 0-28.5-11.5T40-480q0-17 11.5-28.5T80-520h80q17 0 28.5 11.5T200-480q0 17-11.5 28.5T160-440H80Zm720 0q-17 0-28.5-11.5T760-480q0-17 11.5-28.5T800-520h80q17 0 28.5 11.5T920-480q0 17-11.5 28.5T880-440h-80ZM451.5-771.5Q440-783 440-800v-80q0-17 11.5-28.5T480-920q17 0 28.5 11.5T520-880v80q0 17-11.5 28.5T480-760q-17 0-28.5-11.5Zm0 720Q440-63 440-80v-80q0-17 11.5-28.5T480-200q17 0 28.5 11.5T520-160v80q0 17-11.5 28.5T480-40q-17 0-28.5-11.5ZM226-678l-43-42q-12-11-11.5-28t11.5-29q12-12 29-12t28 12l42 43q11 12 11 28t-11 28q-11 12-27.5 11.5T226-678Zm494 495-42-43q-11-12-11-28.5t11-27.5q11-12 27.5-11.5T734-282l43 42q12 11 11.5 28T777-183q-12 12-29 12t-28-12Zm-42-495q-12-11-11.5-27.5T678-734l42-43q11-12 28-11.5t29 11.5q12 12 12 29t-12 28l-43 42q-12 11-28 11t-28-11ZM183-183q-12-12-12-29t12-28l43-42q12-11 28.5-11t27.5 11q12 11 11.5 27.5T282-226l-42 43q-11 12-28 11.5T183-183Zm297-297Z",
            )
        }
    }
}

@Composable
fun DarkModeIcon(modifier: Modifier = Modifier) {
    IconWrapper(modifier) {
        Path {
            d(
                "M480-120q-151 0-255.5-104.5T120-480q0-138 90-239.5T440-838q13-2 23 3.5t16 14.5q6 9 6.5 21t-7.5 23q-17 26-25.5 55t-8.5 61q0 90 63 153t153 63q31 0 61.5-9t54.5-25q11-7 22.5-6.5T819-479q10 5 15.5 15t3.5 24q-14 138-117.5 229T480-120Zm0-80q88 0 158-48.5T740-375q-20 5-40 8t-40 3q-123 0-209.5-86.5T364-660q0-20 3-40t8-40q-78 32-126.5 102T200-480q0 116 82 198t198 82Zm-10-270Z",
            )
        }
    }
}

@Composable
fun RoutineIcon(modifier: Modifier = Modifier) {
    IconWrapper(modifier) {
        Path {
            d(
                "M337.5-463Q311-498 289-537q-5 14-6.5 28.5T281-480q0 83 58 141t141 58q14 0 28.5-2t28.5-6q-39-22-74-48.5T396-396q-32-32-58.5-67ZM481-200q-56 0-107-21t-91-61q-40-40-61-91t-21-107q0-51 17-97.5t50-84.5q13-14 32-9.5t27 24.5q21 55 52.5 104t73.5 91q42 42 91 73.5T648-326q20 8 24.5 27t-9.5 32q-38 33-84.5 50T481-200Zm223-192q-16-5-23-20.5t-4-32.5q9-48-6-94.5T621-621q-35-35-80.5-49.5T448-677q-17 3-32-4t-21-23q-6-16 1.5-31t23.5-19q69-15 138 4.5T679-678q51 51 71 120t5 138q-4 17-19 25t-32 3ZM480-840q-17 0-28.5-11.5T440-880v-40q0-17 11.5-28.5T480-960q17 0 28.5 11.5T520-920v40q0 17-11.5 28.5T480-840Zm0 840q-17 0-28.5-11.5T440-40v-40q0-17 11.5-28.5T480-120q17 0 28.5 11.5T520-80v40q0 17-11.5 28.5T480 0Zm255-734q-12-12-12-28.5t12-28.5l28-28q11-11 27.5-11t28.5 11q12 12 12 28.5T819-762l-28 28q-12 12-28 12t-28-12ZM141-141q-12-12-12-28.5t12-28.5l28-28q12-12 28-12t28 12q12 12 12 28.5T225-169l-28 28q-11 11-27.5 11T141-141Zm739-299q-17 0-28.5-11.5T840-480q0-17 11.5-28.5T880-520h40q17 0 28.5 11.5T960-480q0 17-11.5 28.5T920-440h-40Zm-840 0q-17 0-28.5-11.5T0-480q0-17 11.5-28.5T40-520h40q17 0 28.5 11.5T120-480q0 17-11.5 28.5T80-440H40Zm779 299q-12 12-28.5 12T762-141l-28-28q-12-12-12-28t12-28q12-12 28.5-12t28.5 12l28 28q11 11 11 27.5T819-141ZM226-735q-12 12-28.5 12T169-735l-28-28q-11-11-11-27.5t11-28.5q12-12 28.5-12t28.5 12l28 28q12 12 12 28t-12 28Zm170 339Z",
            )
        }
    }
}

@Composable
fun ContentCopyIcon(modifier: Modifier = Modifier) {
    IconWrapper(modifier) {
        Path {
            d(
                "M360-240q-33 0-56.5-23.5T280-320v-480q0-33 23.5-56.5T360-880h360q33 0 56.5 23.5T800-800v480q0 33-23.5 56.5T720-240H360Zm0-80h360v-480H360v480ZM200-80q-33 0-56.5-23.5T120-160v-520q0-17 11.5-28.5T160-720q17 0 28.5 11.5T200-680v520h400q17 0 28.5 11.5T640-120q0 17-11.5 28.5T600-80H200Zm160-240v-480 480Z",
            )
        }
    }
}

@Composable
fun CheckIcon(modifier: Modifier = Modifier) {
    IconWrapper(modifier) {
        Path {
            d(
                "m382-354 339-339q12-12 28-12t28 12q12 12 12 28.5T777-636L410-268q-12 12-28 12t-28-12L182-440q-12-12-11.5-28.5T183-497q12-12 28.5-12t28.5 12l142 143Z",
            )
        }
    }
}

@Composable
fun ArrowForwardIcon(modifier: Modifier = Modifier) {
    IconWrapper(modifier) {
        Path {
            d(
                "M647-440H200q-17 0-28.5-11.5T160-480q0-17 11.5-28.5T200-520h447L451-716q-12-12-11.5-28t12.5-28q12-11 28-11.5t28 11.5l264 264q6 6 8.5 13t2.5 15q0 8-2.5 15t-8.5 13L508-188q-11 11-27.5 11T452-188q-12-12-12-28.5t12-28.5l195-195Z",
            )
        }
    }
}

@Composable
private fun IconWrapper(
    modifier: Modifier = Modifier,
    content: ContentBuilder<SVGSVGElement>,
) {
    Svg(
        attrs =
            modifier.toAttrs {
                width(24)
                height(24)
                viewBox(ViewBox(0, -960, 960, 960))
                fill(SVGFillType.CurrentColor)
            },
        content = content,
    )
}
