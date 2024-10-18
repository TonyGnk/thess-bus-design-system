package com.tonyGnk.thessBus.designSystem.mobile.features.locations.phases.pickTarget

import androidx.compose.runtime.Composable

enum class CollectionBottomSheetType {
    Hidden, Overview, NewInfo, NewPickLocation
}

@Composable
fun CollectionBottomSheet(
    type: CollectionBottomSheetType, onDismissRequest: () -> Unit
) {

//    val state = rememberFlexibleBottomSheetState(
//        isModal = true,
//        skipSlightlyExpanded = true,
//        containSystemBars = true,
//        flexibleSheetSize = FlexibleSheetSize(
//            //   fullyExpanded = 0.94f
//            intermediatelyExpanded = 0.6f,
//            slightlyExpanded = 0.4f
//        ),
//        skipIntermediatelyExpanded = false
//    )
//
//    val cornerRadius by animateDpAsState(
//        targetValue = when (state.targetValue) {
//            FlexibleSheetValue.Hidden -> 24.dp
//            FlexibleSheetValue.FullyExpanded -> 0.dp // rectangle (no rounded corners)
//            FlexibleSheetValue.IntermediatelyExpanded -> 24.dp
//            FlexibleSheetValue.SlightlyExpanded -> 24.dp
//        }, label = ""
//    )
//
//
//    FlexibleBottomSheet(
//        modifier = Modifier.fillMaxSize(),
//        onDismissRequest = onDismissRequest,
//        containerColor = AppColor.background,
//        shape = RoundedCornerShape(cornerRadius),
//        windowInsets = WindowInsets(
//            0.dp, 4.dp, 0.dp, 0.dp
//        ),
//        tonalElevation = 1.dp,
//        sheetState = state,
//    ) {
//        AnimatedContent(type, label = "") {
//            when (it) {
//                CollectionBottomSheetType.Hidden -> {}
//                CollectionBottomSheetType.Overview -> {}
//                CollectionBottomSheetType.NewInfo -> {}
//                CollectionBottomSheetType.NewPickLocation -> {}
//            }
//        }
////        LazyColumn {
////            for (i in 0..26) {
////                item {
////                    Text(
////                        modifier = Modifier
////                            .fillMaxWidth()
////                            .padding(0.dp),
////                        text = "This is Flexible Bottom Sheet",
////                        textAlign = TextAlign.Center,
////                    )
////                }
////            }
////        }
//    }
}
