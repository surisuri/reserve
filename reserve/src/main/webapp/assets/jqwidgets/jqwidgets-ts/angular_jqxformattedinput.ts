/*
jQWidgets v5.4.0 (2017-Oct)
Copyright (c) 2011-2017 jQWidgets.
License: https://jqwidgets.com/license/
*/
/// <reference path="jqwidgets.d.ts" />
import '../jqwidgets/jqxcore.js';
import '../jqwidgets/jqxformattedinput.js';
import { Component, Input, Output, EventEmitter, ElementRef, forwardRef, OnChanges, SimpleChanges, ChangeDetectionStrategy } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

const noop = () => { };
declare let JQXLite: any;

export const CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR: any = {
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(() => jqxFormattedInputComponent),
    multi: true
}

@Component({
    selector: 'jqxFormattedInput',
    template: '<div><input type="text" [(ngModel)]="ngValue"><div></div><div></div></div>',
    providers: [CUSTOM_INPUT_CONTROL_VALUE_ACCESSOR],
    changeDetection: ChangeDetectionStrategy.OnPush
})

export class jqxFormattedInputComponent implements ControlValueAccessor, OnChanges 
{
   @Input('disabled') attrDisabled: Boolean;
   @Input('decimalNotation') attrDecimalNotation: any;
   @Input('dropDown') attrDropDown: Boolean;
   @Input('dropDownWidth') attrDropDownWidth: Number | String;
   @Input('min') attrMin: Number | String;
   @Input('max') attrMax: Number | String;
   @Input('placeHolder') attrPlaceHolder: String;
   @Input('popupZIndex') attrPopupZIndex: Number;
   @Input('roundedCorners') attrRoundedCorners: Boolean;
   @Input('rtl') attrRtl: Boolean;
   @Input('radix') attrRadix: Number | String;
   @Input('spinButtons') attrSpinButtons: Boolean;
   @Input('spinButtonsStep') attrSpinButtonsStep: Number;
   @Input('template') attrTemplate: any;
   @Input('theme') attrTheme: String;
   @Input('upperCase') attrUpperCase: Boolean;
   @Input('value') attrValue: String;
   @Input('width') attrWidth: String | Number;
   @Input('height') attrHeight: String | Number;

   @Input('auto-create') autoCreate: boolean = true;

   properties: string[] = ['disabled','decimalNotation','dropDown','dropDownWidth','height','min','max','placeHolder','popupZIndex','roundedCorners','rtl','radix','spinButtons','spinButtonsStep','template','theme','upperCase','value','width'];
   host: any;
   elementRef: ElementRef;
   widgetObject:  jqwidgets.jqxFormattedInput;

   private onTouchedCallback: () => void = noop;
   private onChangeCallback: (_: any) => void = noop;

   constructor(containerElement: ElementRef) {
      this.elementRef = containerElement;
   }

   ngOnInit() {
      if (this.autoCreate) {
         this.createComponent(); 
      }
   }; 

   ngOnChanges(changes: SimpleChanges) {
      if (this.host) {
         for (let i = 0; i < this.properties.length; i++) {
            let attrName = 'attr' + this.properties[i].substring(0, 1).toUpperCase() + this.properties[i].substring(1);
            let areEqual: boolean;

            if (this[attrName] !== undefined) {
               if (typeof this[attrName] === 'object') {
                  if (this[attrName] instanceof Array) {
                     areEqual = this.arraysEqual(this[attrName], this.host.jqxFormattedInput(this.properties[i]));
                  }
                  if (areEqual) {
                     return false;
                  }

                  this.host.jqxFormattedInput(this.properties[i], this[attrName]);
                  continue;
               }

               if (this[attrName] !== this.host.jqxFormattedInput(this.properties[i])) {
                  this.host.jqxFormattedInput(this.properties[i], this[attrName]); 
               }
            }
         }
      }
   }

   arraysEqual(attrValue: any, hostValue: any): boolean {
      if (attrValue.length != hostValue.length) {
         return false;
      }
      for (let i = 0; i < attrValue.length; i++) {
         if (attrValue[i] !== hostValue[i]) {
            return false;
         }
      }
      return true;
   }

   manageAttributes(): any {
      let options = {};
      for (let i = 0; i < this.properties.length; i++) {
         let attrName = 'attr' + this.properties[i].substring(0, 1).toUpperCase() + this.properties[i].substring(1);
         if (this[attrName] !== undefined) {
            options[this.properties[i]] = this[attrName];
         }
      }
      return options;
   }

   moveClasses(parentEl: HTMLElement, childEl: HTMLElement): void {
      let classes: any = parentEl.classList;
      if (classes.length > 0) {
        childEl.classList.add(...classes);
      }
      parentEl.className = '';
   }

   moveStyles(parentEl: HTMLElement, childEl: HTMLElement): void {
      let style = parentEl.style.cssText;
      childEl.style.cssText = style
      parentEl.style.cssText = '';
   }

   createComponent(options?: any): void {
      if (options) {
         JQXLite.extend(options, this.manageAttributes());
      }
      else {
        options = this.manageAttributes();
      }
      this.host = JQXLite(this.elementRef.nativeElement.firstChild);

      this.moveClasses(this.elementRef.nativeElement, this.host[0]);
      this.moveStyles(this.elementRef.nativeElement, this.host[0]);

      if (this.attrRtl !== true) {
          if (this.attrSpinButtons === false) {
              this.host.children()[1].remove();
          }
          if (this.attrDropDown !== true) {
              this.host.children()[1].remove();
          }
      } else if (this.attrRtl === true) {
          this.host.children()[1].remove();
          this.host.children()[1].remove();
          if (this.attrSpinButtons !== false) {
              this.host.prepend('<div></div>');
          }
          if (this.attrDropDown === true) {
              this.host.prepend('<div></div>');
          }
      }
      this.__wireEvents__();
      this.widgetObject = jqwidgets.createInstance(this.host, 'jqxFormattedInput', options);

      this.__updateRect__();
   }

   createWidget(options?: any): void {
        this.createComponent(options);
   }

   __updateRect__() : void {
      this.host.css({ width: this.attrWidth, height: this.attrHeight });
   }

   get ngValue(): any {
       if (this.widgetObject)
           return this.host.val();
       return '';
   }

   set ngValue(value: any) {
       if (this.widgetObject) {
           this.onChangeCallback(value);
       }
   }

   writeValue(value: any): void {
       if(this.widgetObject) {
           this.host.jqxFormattedInput('val', value);
       }
   }

   registerOnChange(fn: any): void {
       this.onChangeCallback = fn;
   }

   registerOnTouched(fn: any): void {
       this.onTouchedCallback = fn;
   }

   setOptions(options: any) : void {
      this.host.jqxFormattedInput('setOptions', options);
   }

   // jqxFormattedInputComponent properties
   disabled(arg?: boolean) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('disabled', arg);
      } else {
          return this.host.jqxFormattedInput('disabled');
      }
   }

   decimalNotation(arg?: string) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('decimalNotation', arg);
      } else {
          return this.host.jqxFormattedInput('decimalNotation');
      }
   }

   dropDown(arg?: boolean) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('dropDown', arg);
      } else {
          return this.host.jqxFormattedInput('dropDown');
      }
   }

   dropDownWidth(arg?: Number | String) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('dropDownWidth', arg);
      } else {
          return this.host.jqxFormattedInput('dropDownWidth');
      }
   }

   height(arg?: Number | String) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('height', arg);
      } else {
          return this.host.jqxFormattedInput('height');
      }
   }

   min(arg?: Number | String) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('min', arg);
      } else {
          return this.host.jqxFormattedInput('min');
      }
   }

   max(arg?: Number | String) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('max', arg);
      } else {
          return this.host.jqxFormattedInput('max');
      }
   }

   placeHolder(arg?: string) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('placeHolder', arg);
      } else {
          return this.host.jqxFormattedInput('placeHolder');
      }
   }

   popupZIndex(arg?: number) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('popupZIndex', arg);
      } else {
          return this.host.jqxFormattedInput('popupZIndex');
      }
   }

   roundedCorners(arg?: boolean) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('roundedCorners', arg);
      } else {
          return this.host.jqxFormattedInput('roundedCorners');
      }
   }

   rtl(arg?: boolean) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('rtl', arg);
      } else {
          return this.host.jqxFormattedInput('rtl');
      }
   }

   radix(arg?: Number | String) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('radix', arg);
      } else {
          return this.host.jqxFormattedInput('radix');
      }
   }

   spinButtons(arg?: boolean) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('spinButtons', arg);
      } else {
          return this.host.jqxFormattedInput('spinButtons');
      }
   }

   spinButtonsStep(arg?: number) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('spinButtonsStep', arg);
      } else {
          return this.host.jqxFormattedInput('spinButtonsStep');
      }
   }

   template(arg?: string) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('template', arg);
      } else {
          return this.host.jqxFormattedInput('template');
      }
   }

   theme(arg?: string) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('theme', arg);
      } else {
          return this.host.jqxFormattedInput('theme');
      }
   }

   upperCase(arg?: boolean) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('upperCase', arg);
      } else {
          return this.host.jqxFormattedInput('upperCase');
      }
   }

   value(arg?: string) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('value', arg);
      } else {
          return this.host.jqxFormattedInput('value');
      }
   }

   width(arg?: Number | String) : any {
      if (arg !== undefined) {
          this.host.jqxFormattedInput('width', arg);
      } else {
          return this.host.jqxFormattedInput('width');
      }
   }


   // jqxFormattedInputComponent functions
   close(): void {
      this.host.jqxFormattedInput('close');
   }

   destroy(): void {
      this.host.jqxFormattedInput('destroy');
   }

   focus(): void {
      this.host.jqxFormattedInput('focus');
   }

   open(): void {
      this.host.jqxFormattedInput('open');
   }

   render(): void {
      this.host.jqxFormattedInput('render');
   }

   refresh(): void {
      this.host.jqxFormattedInput('refresh');
   }

   selectAll(): void {
      this.host.jqxFormattedInput('selectAll');
   }

   selectFirst(): void {
      this.host.jqxFormattedInput('selectFirst');
   }

   selectLast(): void {
      this.host.jqxFormattedInput('selectLast');
   }

   val(value?: String | Number): any {
      if (value !== undefined) {
         return this.host.jqxFormattedInput("val", value);
      } else {
         return this.host.jqxFormattedInput("val");
      }
   };


   // jqxFormattedInputComponent events
   @Output() onChange = new EventEmitter();
   @Output() onClose = new EventEmitter();
   @Output() onOpen = new EventEmitter();
   @Output() onRadixChange = new EventEmitter();

   __wireEvents__(): void {
      this.host.on('change', (eventData: any) => { this.onChange.emit(eventData); if (eventData.args) this.onChangeCallback(eventData.args.value); });
      this.host.on('close', (eventData: any) => { this.onClose.emit(eventData); });
      this.host.on('open', (eventData: any) => { this.onOpen.emit(eventData); });
      this.host.on('radixChange', (eventData: any) => { this.onRadixChange.emit(eventData); if (eventData.args) this.onChangeCallback(eventData.args.value); });
   }

} //jqxFormattedInputComponent


