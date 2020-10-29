package com.ahmetcet.searchablespinnerwithicons.Tools;


import com.ahmetcet.searchablespinnerwithicons.R;

public class ImageStrToDrawable {

    public static int GetGroupIconImage(String ImageName) {
        try {

            if (ImageName.equals("1")) {
                return R.drawable.ic_baseline_looks_one_24;
            }else if(ImageName.equals("2")){
                return R.drawable.ic_baseline_looks_two_24;
            }else{
                return R.drawable.ic_baseline_remove_circle_24;
            }

        } catch (Exception e) {
        }

        return 1;
    }
}

