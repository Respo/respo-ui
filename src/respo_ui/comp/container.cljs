
(ns respo-ui.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo.macros :refer [defcomp cursor-> div span input <>]]
            [respo.comp.space :refer [=<]]
            [respo-ui.core :as ui]
            [respo-ui.colors :as colors]
            [respo-ui.comp.sidebar :refer [comp-sidebar]]
            [respo-ui.comp.home :refer [comp-home]]
            [respo-ui.comp.colors-page :refer [comp-colors-page]]
            [respo-ui.comp.widgets-page :refer [comp-widgets-page]]
            [respo-ui.comp.layouts-page :refer [comp-layouts-page]]
            [respo-ui.comp.fonts-page :refer [comp-fonts-page]]
            [respo-ui.comp.components :refer [comp-components-page]]
            [respo-ui.comp.navbar :refer [comp-navbar]]
            [respo-ui.comp.icons-page :refer [comp-icons-page]]))

(def style-content {:padding 8})

(defcomp
 comp-container
 (store)
 (let [router (first (:path (:router store))), states (:states store)]
   (div
    {:style (merge ui/fullscreen ui/row ui/global {:padding-top 32})}
    (comp-sidebar (or (:name router) "index.html"))
    (div
     {:style (merge ui/flex style-content)}
     (case (:name router)
       nil (comp-home)
       "home" (comp-home)
       "index.html" (comp-home)
       "dev.html" (comp-home)
       "colors.html" (comp-colors-page)
       "widgets.html" (cursor-> :widgets comp-widgets-page states)
       "layouts.html" (comp-layouts-page)
       "fonts.html" (comp-fonts-page)
       "components.html" (cursor-> :components comp-components-page states)
       "icons.html" (comp-icons-page)
       (<> (pr-str router)))))))
