import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Next5daysCellComponent } from './next5days-cell.component';

describe('Next5daysCellComponent', () => {
  let component: Next5daysCellComponent;
  let fixture: ComponentFixture<Next5daysCellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Next5daysCellComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Next5daysCellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
